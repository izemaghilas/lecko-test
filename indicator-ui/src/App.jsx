import { useState, useEffect } from "react";

import * as msgDataClient from "./msgdataclient/MsgdataClient";

import ActivityIndicator from "./components/ActivityIndicator";
import EmailsIndicatorChart from "./components/EmailsIndicatorChart";

import "./App.css";

function App() {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(()=>{
    msgDataClient.getNumberOfEmailsEvolution(setData, setIsLoading);
  }, []);

  return (
    <div className="App">
      <main className="App-body">
        {
          isLoading? 
            <ActivityIndicator />
          :
            <EmailsIndicatorChart numberOfEmailsPerDateData={data} />
        }
      </main>
    </div>
  );
}

export default App;
