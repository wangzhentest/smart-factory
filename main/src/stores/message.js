import { defineStore } from "pinia";
import { ref, computed } from 'vue'
import axios from "axios";

export const useMessageStore = defineStore('message', () => {
    const employees = ref([]);
    const safeEmployees = ref([]);

    const getEmployees = async () => {
        const response = await axios.get(`http://localhost:8081/get_Employees`);
        employees.value = response.data;
    }

    const getSafeEmployees = async () => {
        const response = await axios.get(`http://localhost:8081/getDepartmentEmployees?department=${'安全部门'}`);
        safeEmployees.value = response.data;
        console.log("getSafeEmployees")
    }

    return {
        employees,
        safeEmployees,
        getEmployees,
        getSafeEmployees
    }
})