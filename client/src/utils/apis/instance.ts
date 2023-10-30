import axios, { InternalAxiosRequestConfig } from 'axios';

export const instance = axios.create({
	baseURL: '/api/',
	headers: {
		'Access-Control-Allow-Origin': '*',
		'Content-Type': 'application/json',
	},
});

// token 인터셉터
instance.interceptors.request.use((config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
	const token = localStorage.getItem('token');
	const modifiedConfig = { ...config };

	if (token) {
		modifiedConfig.headers.Authorization = `Bearer ${token}`;
		return modifiedConfig;
	}

	return config;
});

export default instance;
