async function fetchData(endpoint, callback) {
    try {
        const response = await fetch(endpoint);
        const data = await response.json();

        if (data.success) {
            const employees = data.result;
            callback(employees);
        } else {
            console.error('Failed to fetch data:', data.message);
        }
    } catch (error) {
        console.error('Failed to fetch data:', error);
    }
}
