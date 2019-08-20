import { Router } from 'express';
import inventoryRoutes from './routes/inventoryRoutes';

const router = Router();

router.use('/inventory', inventoryRoutes);

export default router;
