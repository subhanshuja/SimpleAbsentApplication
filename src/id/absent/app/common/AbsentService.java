package id.absent.app.common;

import id.absent.app.model.Absent;

public interface AbsentService {

    void insertAbsent(Absent absent) throws Exception;
    void updateAbsent(Absent absent) throws Exception;
}
