async function fetchData(endpoint, callback, requestOption = {
    method: "GET"
}) {
    try {
        const response = await fetch(endpoint, requestOption);
        const apiResponse = await response.json();
        callback(apiResponse);
    } catch (error) {
        console.error('Failed to fetch data:', error);
    }
}
