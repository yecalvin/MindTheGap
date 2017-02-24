package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLLineParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {

        String id = stn.getID();

        List<String> lineIds = new LinkedList<>();
        for(Line next: stn.getLines()){
            lineIds.add(next.getId());
        }

        String lineIdsString = "";

        for(String next: lineIds){
            lineIdsString = lineIdsString + next + ",";
        }
        lineIdsString = lineIdsString.substring(0,lineIdsString.length() - 1 );


        String request = "https://api.tfl.gov.uk/Line/"+ lineIdsString + "/Arrivals?stopPointId=" + id + "&app_id=&app_key=";

        // TODO Phase 2 Task 7

        return new URL(request.trim());
    }
}
