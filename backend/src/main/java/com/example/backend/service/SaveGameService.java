package com.example.backend.service;

import java.io.IOException;
import java.io.OutputStream;

public interface SaveGameService {
    void saveFile(OutputStream stream) throws IOException;
}
