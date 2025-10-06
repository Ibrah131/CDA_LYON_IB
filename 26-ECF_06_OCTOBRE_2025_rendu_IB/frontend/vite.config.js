import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

const BACK = process.env.VITE_BACKEND_ORIGIN || 'http://localhost:3000' // adapte si besoin

export default defineConfig({
    plugins: [react()],
    server: {
        port: 5173,
        proxy: {
            '/api': {
                target: BACK,
                changeOrigin: true
            }
        }
    }
})
