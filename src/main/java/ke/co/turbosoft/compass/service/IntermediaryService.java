package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.vo.Result;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface IntermediaryService {

    Result<List<Intermediary>> findAll(String actionUsername);

    /* Result<List<Agent>> findAllAgents(String actionUsername);
    Result<List<Broker>> findAllBrokers(String actionUsername);
    Result<List<Agency>> findAllAgencies(String actionUsername);

    Result<Agent> addAgent(Agent agent,String actionUsername);
    Result<Broker> addBroker(Broker broker,String actionUsername);
    Result<Agency> addAgency(Agency agency,String actionUsername);       */

    //List<E> findAll<Agent>();

    Result<Intermediary> find(Integer idIntermediary, String actionUsername);
    Result<Intermediary> remove(Integer idIntermediary, String actionUsername);

    Result<Broker> addBroker(
            String PIN,
            IntermediaryType type,
            LocalDate joinDate,
            String email,
            String tel,
            String name,
            String street,
            String town,
            String postalAddress,
            String actionUsername);

    Result<Broker> addAgency(
            String PIN,
            IntermediaryType type,
            LocalDate joinDate,
            String email,
            String tel,
            String name,
            String street,
            String town,
            String postalAddress,
            String actionUsername);

    Result<Broker> addAgent(
            String PIN,
            IntermediaryType type,
            LocalDate joinDate,
            String email,
            String tel,
            String firstName,
            String surname,
            String otherNames,
            String actionUsername);

}
