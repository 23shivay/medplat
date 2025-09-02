package com.argusoft.medplat.ingestion;



import com.argusoft.medplat.ingestion.IngestionHandler;
import com.argusoft.medplat.ncddnhdd.dao.MemberHypertensionDetailDao;
import com.argusoft.medplat.ncddnhdd.dto.*;


import com.argusoft.medplat.ncddnhdd.service.NcdDnhddService;
import com.argusoft.medplat.ncddnhdd.model.MemberHypertensionDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class HypertensionIngestionHandler implements IngestionHandler<MemberHyperTensionDto> {


    private static final Logger LOGGER = LoggerFactory.getLogger(HypertensionIngestionHandler.class);

    @Autowired
    private NcdDnhddService ncdDnhddService;

    @Autowired
    @Qualifier("clickhouseJdbcTemplate")
    private JdbcTemplate clickhouseJdbcTemplate;

    @Override
//    public void processAndPersist(MemberHypertensionDetail detail) {
//
//
//
//
//        try {
//            // Extract values
//            Integer memberId = detail.getMemberId();
//            java.util.Date screeningDate = detail.getScreeningDate();
//            Integer systolicBp = detail.getSystolicBp();
//
//            // Insert to ClickHouse
//            String insertSql = "INSERT INTO testing.hypertension_data (memberId, screeningDate, systolicBp) VALUES (?, ?, ?)";
//
//            clickhouseJdbcTemplate.update(
//                    insertSql,
//                    memberId,
//                    new Date(screeningDate.getTime()),
//                    systolicBp
//            );
//
//            LOGGER.info("📊 Inserted into ClickHouse: memberId={}, screeningDate={}, systolicBp={}",
//                    memberId, screeningDate, systolicBp);
//        } catch (Exception e) {
//            LOGGER.error("❌ Error inserting into ClickHouse", e);
//        }
//
//
//    }

    public void processAndPersist(MemberHyperTensionDto detail) {

        System.out.println("🔥 Simulating failure for retry test...");
        throw new RuntimeException("Simulated failure for retry testing");

//        try {
//            // Extract values
//            Integer memberId = detail.getMemberId();
//            java.util.Date screeningDate = detail.getScreeningDate();
//            Integer systolicBp = detail.getSystolicBloodPressure();
//
//
//            // ✅ Save to PostgreSQL via service layer
//            ncdDnhddService.saveHypertension(detail);
//
//            LOGGER.info("✅ Saved to PostgreSQL: memberId={}, screeningDate={}, systolicBp={}",
//                    memberId, screeningDate);
//
//            // ✅ Save to ClickHouse
//            String insertSql = "INSERT INTO testing.hypertension_data (memberId, screeningDate, systolicBp) VALUES (?, ?, ?)";
//
//            clickhouseJdbcTemplate.update(
//                    insertSql,
//                    memberId,
//                    new Date(screeningDate.getTime()),
//                    systolicBp
//
//            );
//
//            LOGGER.info("📊 Inserted into ClickHouse: memberId={}, screeningDate={}, systolicBp={}",
//                    memberId, screeningDate);
//        } catch (Exception e) {
//            LOGGER.error("❌ Error during dual persistence (PostgreSQL + ClickHouse)", e);
//        }
    }


}

