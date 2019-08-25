import { Router } from 'express';
import inventoryRoutes from './routes/inventoryRoutes';
import ordersRoutes from './routes/ordersRoutes';

const router = Router();

router.use('/inventory', inventoryRoutes);

router.use('/orders', ordersRoutes)

export default router;
