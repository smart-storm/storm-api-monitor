package com.smartstorm;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class jsonDownloaderTest {
    @Test
    void checkWrongAddress() {
        assertThrows(IOException.class, ()->jsonDownloader.readJsonFromUrl("WRONG"));
    }
}
