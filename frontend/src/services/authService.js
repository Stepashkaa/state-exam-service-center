import axiosClient from '../api/axiosClient'

export const authService = {
  login: async (data) => {
    const response = await axiosClient.post('/auth/login', data)
    return response.data
  },

  register: async (data) => {
    const response = await axiosClient.post('/auth/register', data)
    return response.data
  },
}