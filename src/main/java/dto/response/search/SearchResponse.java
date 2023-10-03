package dto.response.search;

import dto.response.ResponseType;

public class SearchResponse {
//    Может нужно будет заменить Enum на String
    ResponseType type = ResponseType.SEARCH;
    Result results;
}
