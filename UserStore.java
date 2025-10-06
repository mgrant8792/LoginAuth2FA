import java.io.*;
import java.util.*;

public class UserStore {
    private final File dbFile;
    private final Map<String,String> map = new HashMap<>();

    public UserStore(String path) throws IOException {
        this.dbFile = new File(path);
        if (dbFile.exists()) load();
    }

    private void load() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(dbFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("=")) continue;
                String[] kv = line.split("=", 2);
                map.put(kv[0], kv[1]);
            }
        }
    }

    public void save() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(dbFile))) {
            for (Map.Entry<String,String> e : map.entrySet()) {
                pw.println(e.getKey() + "=" + e.getValue());
            }
        }
    }

    public boolean exists(String user) { return map.containsKey(user); }

    public void createUser(String user, String saltB64, String hashB64) {
        map.put(user, saltB64 + ":" + hashB64);
    }

    public String[] getSaltHash(String user) {
        String v = map.get(user);
        if (v == null) return null;
        String[] parts = v.split(":");
        return new String[]{parts[0], parts[1]};
    }
}

