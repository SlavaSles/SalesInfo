package dto.response.search;

import dto.response.Response;
import dto.response.ResponseType;

public class SearchResponse extends Response {
//    Может нужно будет заменить Enum на String
    private ResponseType type = ResponseType.SEARCH;
    private Result results;

    public SearchResponse() {
    }

    public SearchResponse(ResponseType type) {
        this.type = type;
        this.results = new Result();
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "type=" + type +
                ", results=" + results +
                '}';
    }
}
