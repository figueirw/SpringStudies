package br.com.devdojo.awesome.error;

import java.util.Map;
import java.util.List;

public class ValidationErrorDetails extends ErrorDetails {

    private Map<String, List<String>> field;
    private Map<String, List<String>> fieldMessage;

    public Map<String, List<String>> getField() {
        return field;
    }

    public Map<String, List<String>> getFieldMessage() {
        return fieldMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private Long timeStamp;
        private String developerMessage;
        private Map<String, List<String>> field;
        private Map<String, List<String>> fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder withTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder withDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder withField(Map<String, List<String>> field) {
            this.field = field;
            return this;
        }

        public Builder withFieldMessage(Map<String, List<String>> fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setDeveloperMessage(developerMessage);
            validationErrorDetails.setTitle(title);
            validationErrorDetails.setTimeStamp(timeStamp);
            validationErrorDetails.setDetail(detail);
            validationErrorDetails.setStatus(status);
            validationErrorDetails.field = field;
            validationErrorDetails.fieldMessage = fieldMessage;

            return validationErrorDetails;
        }
    }

}
