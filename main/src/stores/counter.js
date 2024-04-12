import { defineStore } from "pinia";
import { ref, computed } from 'vue'
import axios from "axios";

export const useCounterStore = defineStore('counter', () => {
    const list = ref([]);

    const getList = async (date) => {
        const response = await axios.get(`http://localhost:8081/getTodayAttendance?date=${date}`);
        list.value = response.data;
    }

    const countState = (state) => computed(() => list.value.filter(item => item.state === state).length);

    const attendanceRate = computed(() => {
        const total = list.value.length;
        const attendanceCount = countState(1).value;
        if (total === 0) {
            return 0; //
        } else {
            return (attendanceCount / total) * 100;
        }
    });

    return {
        list,
        getList,
        countState,
        attendanceRate
    }
})
