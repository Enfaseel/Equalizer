package sample;

import java.io.FileInputStream;
import java.io.IOException;

class Serialize {

    static byte[] serialize() throws IOException {
        FileInputStream fin = new FileInputStream(Controller.getTrack());
        byte[] bytes = new byte[(int)Controller.getTrack().length()];
        int bytesAmount = fin.read(bytes);
        fin.close();
        return bytes;
    }


}
