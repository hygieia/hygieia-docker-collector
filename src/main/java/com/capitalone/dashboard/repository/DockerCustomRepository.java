package com.capitalone.dashboard.repository;

import org.springframework.stereotype.Component;

/*Custom Repository to provide data to component chart on the screen
Based on Aggregate & Group by Data*/

@Component
public class DockerCustomRepository {
	/*
	 * private final SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	 * 
	 * private final SimpleDateFormat fromDateFormat = new
	 * SimpleDateFormat("dd-MMM"); private final SimpleDateFormat toDateFormat = new
	 * SimpleDateFormat("dd-MMM-yyyy");
	 * 
	 * private MongoTemplate mongoTemplate;
	 * 
	 * @Autowired public DockerCustomRepository(MongoTemplate mongoTemplate) { //
	 * TODO Auto-generated constructor stub this.mongoTemplate = mongoTemplate; }
	 * 
	 * public JSONObject findDistinctRunStatus(String workSpaceId) { Criteria
	 * criteria = new Criteria("workspaceId").is(workSpaceId);
	 * org.springframework.data.mongodb.core.query.Query query = new
	 * org.springframework.data.mongodb.core.query.Query();
	 * query.addCriteria(criteria); List<Run> runs = mongoTemplate.find(query,
	 * Run.class, "run"); JSONObject uniqeStatus = new JSONObject();
	 * 
	 * for (Run run : runs) { if (uniqeStatus.containsKey(run.getStatus())) {
	 * Integer count = Integer.parseInt(uniqeStatus.get(run.getStatus()).toString())
	 * + 1; uniqeStatus.put(run.getStatus(), count); } else {
	 * uniqeStatus.put(run.getStatus(), 1);
	 * 
	 * } }
	 * 
	 * return uniqeStatus; }
	 * 
	 * public JSONObject findDistinctRunStatusBetweenDateTime(String workSpace) {
	 * Calendar cal = Calendar.getInstance();
	 * cal.setTimeZone(TimeZone.getTimeZone("UTC")); cal.add(Calendar.HOUR, -(12));
	 * 
	 * Criteria criteria = new Criteria("workspaceId").is(workSpace);
	 * 
	 * org.springframework.data.mongodb.core.query.Query query = new
	 * org.springframework.data.mongodb.core.query.Query();
	 * 
	 * query.addCriteria(criteria); List<Run> runs = mongoTemplate.find(query,
	 * Run.class, "run"); JSONObject uniqeStatus = new JSONObject();
	 * 
	 * for (Run run : runs) { if (uniqeStatus.containsKey(run.getStatus())) {
	 * Integer count = Integer.parseInt(uniqeStatus.get(run.getStatus()).toString())
	 * + 1; uniqeStatus.put(run.getStatus(), count); } else {
	 * uniqeStatus.put(run.getStatus(), 1);
	 * 
	 * } } return uniqeStatus; }
	 * 
	 * public ComponentData getTerraformDetailAggregateRun(String workSpace, String
	 * type, Integer from, String status) { ComponentData componentData = new
	 * ComponentData();
	 * 
	 * Calendar calTemp = Calendar.getInstance();
	 * calTemp.setTimeZone(TimeZone.getTimeZone("UTC"));
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * cal.setTimeZone(TimeZone.getTimeZone("UTC")); cal.add(Calendar.HOUR, -(12));
	 * 
	 * Map<String, List<Series>> statusMap = new HashMap<String, List<Series>>();
	 * List<Series> multi = new ArrayList<Series>(); List<Series> temp = null; if
	 * (!type.isEmpty()) {
	 * 
	 * List<Date> calList = getGroupByDate(type, from); Collections.sort(calList);
	 * 
	 * for (int i = 1; i < calList.size(); i++) {
	 * 
	 * Date dateUtilFrom = (Date) calList.get(i - 1); Date dateUtilNow = (Date)
	 * calList.get(i);
	 * 
	 * Aggregation agg =
	 * newAggregation(match(Criteria.where("createdAt").gte(dateUtilFrom).lte(
	 * dateUtilNow)), group("status").count().as("value"),
	 * project("value").and("status").as("name"), sort(Sort.Direction.DESC,
	 * "value"));
	 * 
	 * JSONParser jsonParser = new JSONParser(); DBObject obj =
	 * mongoTemplate.aggregate(agg, Run.class, Series.class).getRawResults();
	 * 
	 * List<Series> asList = new ArrayList<Series>(); // Modelling the data in the
	 * Chart Data Format if (obj.containsKey("result")) { try { JSONArray asArray =
	 * ((JSONArray) jsonParser.parse(obj.get("result").toString()));
	 * 
	 * for (int k = 0; k < asArray.size(); k++) { Series series = new Series();
	 * 
	 * try { JSONObject jsonObj = (JSONObject)
	 * jsonParser.parse(asArray.get(k).toString());
	 * 
	 * if (statusMap.containsKey(jsonObj.get("_id").toString().toUpperCase())) {
	 * temp = (List<Series>)
	 * statusMap.get(jsonObj.get("_id").toString().toUpperCase()); } else { temp =
	 * new ArrayList<Series>(); }
	 * 
	 * series.setName("From " + formatDateToReadableString(dateUtilFrom,
	 * fromDateFormat, false) + " - " + formatDateToReadableString(dateUtilNow,
	 * toDateFormat, true)); series.setValue(jsonObj.get("value"));
	 * 
	 * temp.add(series); statusMap.put(jsonObj.get("_id").toString().toUpperCase(),
	 * temp);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * asList.add(series);
	 * 
	 * }
	 * 
	 * } catch (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } Series multiSeries = new Series();
	 * multiSeries.setName("From " + formatDateToReadableString(dateUtilFrom,
	 * fromDateFormat, false) + " - " + formatDateToReadableString(dateUtilNow,
	 * toDateFormat, true)); multiSeries.setValue(asList); multi.add(multiSeries); }
	 * }
	 * 
	 * }
	 * 
	 * componentData.setStatus(STATUS.PASS);
	 * 
	 * componentData.setData(multi);
	 * 
	 * return componentData; }
	 * 
	 * public ComponentData getTerraformDetailCountRun(String workSpace, String
	 * status, String type, Integer from) { ComponentData componentData = new
	 * ComponentData();
	 * 
	 * List<Series> data = new ArrayList<Series>(); Calendar calTemp =
	 * Calendar.getInstance(); calTemp.setTimeZone(TimeZone.getTimeZone("UTC"));
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * cal.setTimeZone(TimeZone.getTimeZone("UTC")); cal.add(Calendar.HOUR, -(12));
	 * 
	 * Map<String, Long> statusMap = new HashMap<String, Long>(); List<Series> multi
	 * = new ArrayList<Series>();
	 * 
	 * if (!type.isEmpty()) {
	 * 
	 * List<Date> calList = getGroupByDate(type, from); Collections.sort(calList);
	 * 
	 * for (int i = 1; i < calList.size(); i++) {
	 * 
	 * Date dateUtilFrom = (Date) calList.get(i - 1); Date dateUtilNow = (Date)
	 * calList.get(i);
	 * 
	 * Aggregation agg =
	 * newAggregation(match(Criteria.where("createdAt").gte(dateUtilFrom).lte(
	 * dateUtilNow)), group("status").count().as("value"),
	 * project("value").and("status").as("name"), sort(Sort.Direction.DESC,
	 * "value"));
	 * 
	 * JSONParser jsonParser = new JSONParser(); DBObject obj =
	 * mongoTemplate.aggregate(agg, Run.class, Series.class).getRawResults();
	 * 
	 * List<Series> asList = new ArrayList<Series>(); if (obj.containsKey("result"))
	 * { try { JSONArray asArray = ((JSONArray)
	 * jsonParser.parse(obj.get("result").toString()));
	 * 
	 * for (int k = 0; k < asArray.size(); k++) { Series series = new Series();
	 * 
	 * try { JSONObject jsonObj = (JSONObject)
	 * jsonParser.parse(asArray.get(k).toString());
	 * series.setName(jsonObj.get("_id").toString().toUpperCase());
	 * series.setValue(jsonObj.get("value"));
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * asList.add(series);
	 * 
	 * }
	 * 
	 * } catch (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } Series multiSeries = new Series();
	 * multiSeries.setName("From " + formatDateToReadableString(dateUtilFrom,
	 * fromDateFormat, false) + " - " + formatDateToReadableString(dateUtilNow,
	 * toDateFormat, true)); multiSeries.setValue(asList); multi.add(multiSeries); }
	 * }
	 * 
	 * }
	 * 
	 * for (String key : statusMap.keySet()) { Series series = new Series(key,
	 * (statusMap.get(key).toString())); data.add(series); }
	 * 
	 * componentData.setStatus(STATUS.PASS);
	 * 
	 * componentData.setData(multi);
	 * 
	 * return componentData; }
	 * 
	 * 
	 * To create the dates on the X Axis on the Graphs This will populate the X Axis
	 * with the time range selected
	 * 
	 * private List<Date> getGroupByDate(String type, Integer from) { List<Date>
	 * calList = new ArrayList<Date>();
	 * 
	 * Calendar cal = Calendar.getInstance();
	 * cal.setTimeZone(TimeZone.getTimeZone("UTC")); cal.add(Calendar.HOUR, -(12));
	 * 
	 * if (!type.isEmpty()) { switch (type.toUpperCase()) { case "MINUTE":
	 * 
	 * for (int i = from; i >= 0; i--) { Calendar calIn = Calendar.getInstance();
	 * calIn.setTimeZone(TimeZone.getTimeZone("UTC")); calIn.add(Calendar.MINUTE,
	 * -(i)); calList.add(calIn.getTime()); }
	 * 
	 * break;
	 * 
	 * case "DAY":
	 * 
	 * for (int i = from; i >= 0; i--) { Calendar calIn = Calendar.getInstance();
	 * calIn.setTimeZone(TimeZone.getTimeZone("UTC")); calIn.add(Calendar.DATE,
	 * -(i)); calList.add(calIn.getTime()); }
	 * 
	 * break;
	 * 
	 * case "HOUR":
	 * 
	 * for (int i = from; i >= 0; i--) { Calendar calIn = Calendar.getInstance();
	 * calIn.setTimeZone(TimeZone.getTimeZone("UTC")); calIn.add(Calendar.HOUR,
	 * -(i)); calList.add(calIn.getTime()); }
	 * 
	 * break;
	 * 
	 * case "WEEK":
	 * 
	 * for (int i = from; i >= 0; i--) { Calendar calIn = Calendar.getInstance();
	 * calIn.setTimeZone(TimeZone.getTimeZone("UTC"));
	 * calIn.add(Calendar.WEEK_OF_YEAR, -(i)); calList.add(calIn.getTime()); }
	 * 
	 * break;
	 * 
	 * default: break; } }
	 * 
	 * calList.add(cal.getTime());
	 * 
	 * return calList; }
	 * 
	 * private String formatDateToReadableString(Date date, SimpleDateFormat
	 * simpleDateFormat, Boolean hasYear) { String dtStr = null;
	 * 
	 * dtStr = simpleDateFormat.format(date);
	 * 
	 * if (dtStr != null) { String[] dtSplit = dtStr.split("-");
	 * 
	 * if (dtSplit[0].endsWith("1")) { dtSplit[0] = dtSplit[0] + "st"; } else
	 * 
	 * if (dtSplit[0].endsWith("2")) { dtSplit[0] = dtSplit[0] + "nd"; } else
	 * 
	 * if (dtSplit[0].endsWith("3")) { dtSplit[0] = dtSplit[0] + "rd"; } else {
	 * dtSplit[0] = dtSplit[0] + "th"; } dtStr = (dtSplit[0] + dtSplit[1] + (hasYear
	 * ? dtSplit[2] : "")); }
	 * 
	 * return dtStr; }
	 */}
