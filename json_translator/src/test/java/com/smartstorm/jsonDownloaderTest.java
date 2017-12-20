package com.smartstorm;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class jsonDownloaderTest {

    @Test
    void checkWrongAddress() {
        assertThrows(IOException.class, ()->jsonDownloader.readJsonFromUrl("WRONG"));
    }

}
