import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react';
import eslint from 'vite-plugin-eslint';
import tsconfigPaths from 'vite-tsconfig-paths';
import svgr from 'vite-plugin-svgr';

// TODO : froxy 설정 시, url 숨기기(.env)
export default defineConfig(async ({ mode }) => {
	const env = loadEnv(mode, process.cwd(), '');

	return {
		plugins: [react(), eslint(), tsconfigPaths(), svgr()],
		base: '/',
		server: {
			proxy: {
				'/api': {
					target: env.VITE_APP_SERVER_BASE_URL,
					changeOrigin: true,
					rewrite: (path) => path.replace(/%\/api/, ''),
					secure: false,
					ws: true,
				},
			},
		},
	};
});
