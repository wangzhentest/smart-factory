import { defineStore } from "pinia";
import { ref} from 'vue'
import axios from "axios";

export const useEmployeeStore = defineStore('employee', () => {
    const notRemindedMessages = ref([]);

    const getNotRemindedMessages = async () => {
        const response = await axios.get(`http://localhost:8081/get_not_reminded_messages`);
        notRemindedMessages.value = response.data;
    }

    return {
        notRemindedMessages,
        getNotRemindedMessages,
    }
})