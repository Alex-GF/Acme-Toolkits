package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;

public class PatronPatronageReportRepository extends AbstractRepository {

    @Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.patron.userAccount.id = :patronId")
    Collection<PatronageReport> findPatronageReportsByPatronId(int patronId);

    @Query("SELECT pr FROM PatronageReport pr WHERE pr.id = :patronageReportId")
    PatronageReport getPatronageReportById(int patronageReportId);
}
