package UserManagement;

import DataManagement.ImageData;

import java.util.Calendar;

public class ProfileImage extends ImageData {
    private Calendar submitDate;

    public Calendar getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Calendar submitDate) {
        this.submitDate = submitDate;
    }
}
