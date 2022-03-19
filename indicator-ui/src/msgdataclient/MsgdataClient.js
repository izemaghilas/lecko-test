const apiUrl = "http://127.0.0.1:8080/api/v1";

async function getNumberOfEmailsEvolution(setData, setIsLoading) {
    
    try {
        const res = await fetch(apiUrl+"/emails/number-of-emails-evolution", {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors'
        })
        if(res.ok){
            const data = await res.json();
            setData(data);
            setIsLoading(false);
        }
        else{
            setData([]);
            setIsLoading(false);
        }

    } catch (error) {
        setData([]);
        setIsLoading(false);
    }
}

export { getNumberOfEmailsEvolution };