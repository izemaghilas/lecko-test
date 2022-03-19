import { 
    Chart, 
    CategoryScale, 
    LinearScale,
    PointElement,
    LineElement, 
    Tooltip,
    Title,
} from "chart.js";
import { Line } from "react-chartjs-2";

Chart.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Tooltip,
    Title
);

function EmailsIndicatorChart(props) {
    return(
        <Line 
            options={{
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: "Evolution du nombre de mails envoyés",
                        font: {
                            size: 20,
                        }
                    }
                },
                layout: {
                    padding: {
                        left: 20,
                        right: 40,
                        bottom: 30
                    }
                },
                scales: {
                    xAxis: {
                        title: {
                            display: true,
                            text: "Dates"
                        }
                    },
                    yAxis: {
                        title: {
                            display: true,
                            text: "Nomber de mails",
                        }
                    }
                }
            }} 
            data={{
                
                datasets: [{
                    data: props.numberOfEmailsPerDateData.map(e=>{ return {x: e.sentDate, y: e.numberOfEmails} }),
                    borderColor: "rgb(53, 162, 235)",
                    backgroundColor: "rgba(53, 162, 235, 0.5)",
                    xAxisID: "xAxis",
                    yAxisID: "yAxis"
                }]
            }} 
        />
    );
}

export default EmailsIndicatorChart;