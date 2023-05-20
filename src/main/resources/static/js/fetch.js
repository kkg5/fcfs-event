if (location.hostname === 'localhost') {
  HOST = 'http://localhost:8080';
} else {
  HOST = 'https://fcfs.duckdns.org';
}

/**
 * Get Json Response
 * @param {string} url
 * @param {string} method
 * @return {Promise<any>}
 */
function fetchJson(url, method = 'get') {
  return fetch(
      HOST + url,
      {
        method: method,
      },
  ).then((response) => response.json());
}
