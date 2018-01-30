package com.smartstorm;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class jsonDownloaderTest {
    @Test
    void checkWrongAddress() {
        assertThrows(IOException.class, ()->jsonDownloader.readJsonFromUrl("WRONG"));
    }
}
