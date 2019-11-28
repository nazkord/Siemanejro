package communication;

import android.os.AsyncTask;

import java.util.function.Consumer;
import java.util.function.Function;

public class CommunicationAsync<Param, Result> extends AsyncTask<Param, Void, AsyncTaskResult<Result>> {

    private final Function<Param, Result> function;
    private Consumer<Result> consumer;
    private Consumer<Exception> errorHandler;

    public CommunicationAsync(Function<Param, Result> function) {
        this.function = function;
    }


    public CommunicationAsync<Param, Result> onSuccess(Consumer<Result> consumer) {
        this.consumer = consumer;
        return this;
    }

    public CommunicationAsync<Param, Result> onError(Consumer<Exception> errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    @Override
    protected AsyncTaskResult<Result> doInBackground(Param... params) {
        try {
            return new AsyncTaskResult<>(function.apply(params[0]));
        } catch (Exception e) {
            cancel(false);
            return new AsyncTaskResult<>(e);
        }
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<Result> resultAsyncTaskResult) {
        super.onPostExecute(resultAsyncTaskResult);
        consumer.accept(resultAsyncTaskResult.getResult());
    }

    @Override
    protected void onCancelled(AsyncTaskResult<Result> resultAsyncTaskResult) {
        super.onCancelled(resultAsyncTaskResult);
        errorHandler.accept(resultAsyncTaskResult.getException());
    }
}
