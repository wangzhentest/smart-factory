import { defineStore } from "pinia";
import { ref, computed } from 'vue'
import axios from "axios";

export const useIncidentStore = defineStore('incident', () => {
    const FinishedList = ref([]);
    const UnfinishedList = ref([]);

    const getFinishedIncident = async () => {
        const response = await axios.get(`http://localhost:8081/getFinishedIncident`);
        FinishedList.value = response.data;
    }

    const getUnfinishedIncident = async () => {
        const response = await axios.get(`http://localhost:8081/getUnfinishedIncident`);
        UnfinishedList.value = response.data;
    }

    return {
        FinishedList,
        UnfinishedList,
        getFinishedIncident,
        getUnfinishedIncident,
    }
})
