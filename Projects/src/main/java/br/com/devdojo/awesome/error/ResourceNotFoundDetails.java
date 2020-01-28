package br.com.devdojo.awesome.error;

public class ResourceNotFoundDetails extends ErrorDetails {

    private ResourceNotFoundDetails() {
    }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private Long timeStamp;
        private String developerMessage;

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setTimeStamp(timeStamp);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setStatus(status);
            return resourceNotFoundDetails;
        }
    }
}

