<h3>A collector to showcase the Terraform Cloud App details</h3>
https://app.terraform.io

<h3>How is Terraform App Organized?</h3>
 Each Terraform app has Organization > Workspace > run jobs
 * Organization : Model to store the Organization details from Terraform Cloud App
 * Workspace : Model to Store Workpsace belonging to an Organizatiion from Terrafrom Cloud App
 * Run : Model to Store Runs belonging to an Workpsace from Terrafrom Cloud App
 * TerraformCollectorItem: The class extending the CollectorItem storing only the apiToken, which is enough to call the Terraform Cloud   Apis

<h3>Which API this collector calls?</h3>
  The Workspace Cloud App by Terraform provide REST api to call and share details about Organizatiion, Workspace & Run Jobs

<h3>What data this collector Stores?</h3>
  This Collector stores data as Organization, Workspace & Run (The same model are provided in domain)
  https://www.terraform.io/docs/cloud/api/workspaces.html

<h3>Technical Description</h3>
  The Classes and the description
  Terraform Controller: To expose the api calls made from UI to get the Component Data
  TerraformCollectorTask : As with the framework , implemeting Collector Task to regulary collect the details from Terraform Cloud App     and feed into the MongoDb
  TeraafromServiceImpl: To populate the componentData mdel and send back to UI
  TerraformCustomRepository: A MongoTemplate based repository, to do some aggrrgate group by queries against mondo db
  
  <h3>To build docker & mounting the application.properties while docker build</h3>
  docker run -t -i -p port-internal:port-external -v properties_location://hygieia/config  image_name




