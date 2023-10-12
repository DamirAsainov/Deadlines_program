package com.example.endterm;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DeadlinesReader {
    public List<Deadline> updateDeadlines(URL url, User user) throws IOException {
        downloadFile(url, "deadlines.ics");
        System.out.println("1 etap");
        read("deadlines.ics", user);
        System.out.println("2 etap");
        deleteFile("deadlines.ics");
        System.out.println("3 etap");
        DeadlineDB database = new DeadlineDB();
        database.deleteOldDeadlines();
        return database.getDeadlineList(user.group);
    }

    private void downloadFile(URL url, String outputFileName) throws IOException {
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    private void deleteFile(String name) {
        File file = new File(name);

        boolean result = file.delete();
        if (result) {
            System.out.println("File is successfully deleted.");
        } else {
            System.out.println("File deletion failed.");
        }
    }

    private void read(String fileName, User user) {
        BufferedReader br = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            br = new BufferedReader(fileReader);
            String line = br.readLine();
            while (line != null) {
                if (line.equals("BEGIN:VEVENT")) {
                    Deadline deadline = new Deadline();
                    br.readLine();
                    deadline.title = br.readLine().replace("SUMMARY:", "");
                    while (!line.equals("CLASS:PUBLIC")) {
                        line = br.readLine();
                    }
                    br.readLine();
                    br.readLine();
                    br.readLine();
                    deadline.timeEnd = br.readLine().replace("DTEND:", "");
                    deadline.covertTimeEnd();
                    deadline.course = br.readLine().replace("CATEGORIES:", "");

                    if (deadline.IsActual()) {
                        int counter = 0;
                        DeadlineDB database = new DeadlineDB();
                        ResultSet result = database.getDeadline(deadline);
                        try {
                            while (result.next()) {
                                counter++;
                            }
                            if (!(counter > 0)) {
                                database.addDeadline(deadline, user);
                                System.out.println(deadline.title);
                                System.out.println(deadline.timestampEnd.toString());
                                System.out.println(deadline.timeEnd);
                                System.out.println(deadline.course);
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
