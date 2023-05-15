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
function fetchJson(url) {
  return fetch(HOST + url)
      .then((response) => response.json());
}
