if (location.hostname === 'localhost') {
  HOST = 'http://localhost:8080';
} else {
  HOST = 'https://fcfs.duckdns.org';
}

/**
 * Get Json Response
 * @param {string} url
 * @return {Promise<any>}
 */
function getFetchJson(url) {
  return fetch(HOST + url)
      .then((response) => response.json());
}

/**
 * Post and Get Json Response
 * @param {string} url
 * @param {object} body
 * @return {Promise<any>}
 */
function postFetchJson(url, body) {
  return fetch(HOST + url, {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: body,
  }).then((response) => response.json());
}
