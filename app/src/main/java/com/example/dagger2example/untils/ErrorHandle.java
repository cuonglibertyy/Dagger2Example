package com.example.dagger2example.untils;

import com.example.dagger2example.model.ErrorParser;
import com.example.dagger2example.model.error.Error;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorHandle {
    public static Error errorParser(Throwable error) {
        String errMessage;
        Error errorParser = null;
        if (error instanceof HttpException) {
            ResponseBody body = ((HttpException) error).response().errorBody();
            Gson gson = new Gson();
            TypeAdapter<Error> adapter = gson.getAdapter(Error.class);
            try {
                errorParser = adapter.fromJson(body.string());
//                errMessage = errorParser.getErrorMessage();

            } catch (IOException e) {
                e.printStackTrace();
                errMessage = "There is no data";
            }
        } else if (error instanceof TimeoutException) {
//            errorParser.getResults().getErrorInfo().setMessage("Connect to sever take too long, please try again");
        } else {
//            errorParser.getResults().getErrorInfo().setMessage("No internet connection");
        }
        return errorParser;
    }
}
