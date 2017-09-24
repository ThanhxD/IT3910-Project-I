const fs = require('fs'),
      https = require('https'),
      path = require('path');
const access_token = require('./access-token');

function _ensureFilePath(filePath) {
  var dirname = path.dirname(filePath);
  if (fs.existsSync(dirname)) {
    return true;
  }
  _ensureFilePath(dirname);
  fs.mkdirSync(dirname);
}
//=================================== Utils ===================================
function Utils() {}

Utils.prototype = {
    constructor: Utils,
    /**
     * Save object json to file - overwriten mode
     * @param {Object} data - JSON data to append
     * @param {String} file - file name
     */
    saveToFile: function(data, file) {
        let filePath = __dirname + `/../../database/data/raw/${file}`;
        _ensureFilePath(filePath);
        fs.writeFile(filePath, JSON.stringify(data, null, 2), (err) => {
            if (err) {
                throw err;
            }
        });
    },

    /**
     * Prepare an url - path for Graph API
     * @param {Object} params - options to fullfill path
     * @return {String} GraphAPI path
     */
    prepareUrl: function(params) {
        return `https://graph.facebook.com/v2.10/${params.nodeId}/?fields=${params.fields}&access_token=${access_token}`;
    },

    /**
     * Send HTTP Request asynchronously
     * @param {String} url - url to send request
     * @return {Promise} request's result
     */
    request: function(url) {
        return new Promise((resolve, reject) => {
            https.get(url, res => {
                let data = '';
                res.on('data', chunk => {
                    data += chunk;
                });
                res.on('end', () => {
                    resolve(JSON.parse(data));
                });
            }).on('error', err => {
                reject(err.message);
            })
        });
    }
}

module.exports = new Utils;