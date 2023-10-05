package dto.response.search;

import dto.criteria.Criteriya;

public class CriteriyaResult {
    private Criteriya criteriya;
    private CustomerResults results;

    public CriteriyaResult() {
    }

    public CriteriyaResult(Criteriya criteriya, CustomerResults results) {
        this.criteriya = criteriya;
        this.results = results;
    }

    public Criteriya getCriteriya() {
        return criteriya;
    }

    public void setCriteriya(Criteriya criteriya) {
        this.criteriya = criteriya;
    }

    public CustomerResults getResults() {
        return results;
    }

    public void setResults(CustomerResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "\nCriteriyaResult{" +
                "criteriya=" + criteriya +
                ", \nresults=" + results +
                '}';
    }
}
