package com.ferfalk.debounceswitchmapexample.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NextLaunchesDTO implements Serializable {
    @SerializedName("launches")
    private List<Launch> launches;

    public List<Launch> getLaunches() {
        return launches;
    }

    public class Launch implements Serializable {
        @SerializedName("rocket")
        private Rocket rocket;

        public Rocket getRocket() {
            return rocket;
        }

        public class Rocket implements Serializable {
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }
        }

    }
}
