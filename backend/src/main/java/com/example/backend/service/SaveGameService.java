package com.example.backend.service;

import java.io.OutputStream;
import java.util.Date;

public interface SaveGameService {
    void saveFile(Date time, OutputStream stream);
}
