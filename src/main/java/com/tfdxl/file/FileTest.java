package com.tfdxl.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfeng
 */
public class FileTest {

    public static void main(String[] args) throws IOException {

        final List<String> lines = FileUtils.readLines(new File("/Users/tianfeng/Desktop/query_result.csv"));

        List<String> result = new ArrayList<>(lines.size());
        lines.stream().forEach(s -> {
            final String[] strs = s.split(",");
            String ret = String.format("update insurance set insurant = '%s' where vin = '%s';", strs[0], strs[1]);
            System.err.println(ret);
            result.add(ret);
        });

        FileUtils.writeLines(new File("/Users/tianfeng/Desktop/query_result1.csv"), result);
    }
}
