package com.ferfalk.debounceswitchmapexample.data.remote;

import com.ferfalk.debounceswitchmapexample.data.remote.dto.NextLaunchesDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("launch")
    Observable<NextLaunchesDTO> getNextLaunches(@Query("next") int next,
                                                @Query("offset") int offset,
                                                @Query("mode") String mode);
}
