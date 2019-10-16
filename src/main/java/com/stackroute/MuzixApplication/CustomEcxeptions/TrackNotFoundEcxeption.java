package com.stackroute.MuzixApplication.CustomEcxeptions;

public class TrackNotFoundEcxeption extends Exception
    {
        String message;

        public TrackNotFoundEcxeption() {
        }

        public TrackNotFoundEcxeption(String message) {
            super(message);
            this.message = message;
        }
    }
