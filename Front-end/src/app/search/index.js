const SolrNode = require("solr-node");
const topics = require("./json.json");
//const parsedJSON = JSON.parse(topics);
//console.log(topics);
//console.log(topics["14"]);

var client = new SolrNode({
  host: "127.0.0.1",
  port: "8983",
  core: "wiki",
  protocol: "http",
});

for (let topicName in topics) {
  console.log(topics[topicName]);
  client.update(topics[topicName], function (err, result) {
    if (err) {
      console.log(err);
      return;
    }
    console.log("Response:", result.responseHeader);
  });
  //console.log(topic[topic]);
}

// Add
//Solr adds ID to each document by default, however, if you specify an id, solr will use that instead.

//-------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------

// // Delete
const stringQuery = 'title: "book"'; // delete document with id 2
const deleteAllQuery = "*"; // delete all
const objectQUery = { id: "d7497504-22d9-4a22-9635-88dd437712ff" }; // Object query
client.update(stringQuery, function (err, result) {
  if (err) {
    console.log(err);
    return;
  }
  console.log("Response:", result.responseHeader);
});
