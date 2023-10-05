package dto.response.search;

import java.util.ArrayList;
import java.util.List;

public class Result {
//    Возможно надо сразу писать ArrayList
    private List<CriteriyaResult> criteriyaResults;

    public Result() {
        this.criteriyaResults = new ArrayList<>();
    }

    public List<CriteriyaResult> getCriteriaResults() {
        return criteriyaResults;
    }

    public void setCriteriaResults(List<CriteriyaResult> criteriyaResults) {
        this.criteriyaResults = criteriyaResults;
    }

    @Override
    public String toString() {
        return "Result{" +
                "criteriyaResults=" + criteriyaResults +
                '}';
    }
}
