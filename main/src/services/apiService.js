import axios from 'axios';

const apiService = axios.create({
    baseURL: 'http://localhost:8081',
});

export default {

    //Cameras----------------------------------------------------------------------
    async get_cameras(location) {
        try {
            const response = await apiService.get('/getSurveillanceEquipments',{params: {location: location}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    //Incident----------------------------------------------------------------------
    async get_untreated_incidents() {
        try {
            const response = await apiService.get('/getUnfinishedIncident');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    async get_treated_incidents() {
        try {
            const response = await apiService.get('/getFinishedIncident');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    async add_incident(camera_id,incident_type) {
        try {
            const response = await apiService.get('/add_incident', {params: {camera_id: camera_id,incident_type:incident_type,video:"test"}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    //Log----------------------------------------------------------------------
    async get_logs() {
        try {
            const response = await apiService.get('/get_logs');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    //staffs----------------------------------------------------------------------
    async get_employees() {
        try {
            const response = await apiService.get('/get_Employees');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    async get_shifts_employees() {
        try {
            const response = await apiService.get('/get_today_shifts');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    //Message----------------------------------------------------------------------
    async get_messages() {
        try {
            const response = await apiService.get('/get_messages');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    async get_not_reminded_messages() {
        try {
            const response = await apiService.get('/get_not_reminded_messages');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    async remind_message(id) {
        try {
            const response = await apiService.get('/remind_message', {params: {id: id}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    async add_messages(camera_id,alarm_reason) {
        try {
            const response = await apiService.get('/add_messages', {params: {camera_id: camera_id,alarm_reason:alarm_reason}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    async delete_message(id,user_id) {
        try {
            const response = await apiService.get('/delete_message', {params: {id: id,user_id:user_id}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },

    //Summary----------------------------------------------------------------------
    async get_summaries() {
        try {
            const response = await apiService.get('/get_summaries');
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    async get_summary_by_id(incident_id) {
        try {
            const response = await apiService.get('/get_summary_by_id', {params: {incident_id: incident_id}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
    //Attendance
    async getTodayAttendance(date) {
        try {
            const response = await apiService.get('/getTodayAttendance', {params: {date: date}});
            return response.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    },
};
