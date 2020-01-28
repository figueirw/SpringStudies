package br.com.devdojo.awesome.error;

public class ErrorDetails {
    String title;
    int status;
    String detail;
    long timeStamp;
    String developerMessage;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public static final class ErrorDetailsBuilder {
        String title;
        int status;
        String detail;
        long timeStamp;
        String developerMessage;

        private ErrorDetailsBuilder() {
        }

        public static ErrorDetailsBuilder newBuilder() {
            return new ErrorDetailsBuilder();
        }

        public ErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorDetailsBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ErrorDetailsBuilder timeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ErrorDetailsBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetail(detail);
            errorDetails.setTimeStamp(timeStamp);
            errorDetails.setDeveloperMessage(developerMessage);
            return errorDetails;
        }
    }
}
