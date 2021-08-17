package com.ufgec.AP2_2021_1_site_compra_passagens_aereas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class TestLogger4J {
    private static final Logger logger = LogManager.getLogger(TestLogger4J.class.getSimpleName());

    @Test
    void logger() {
        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");
    }
}
