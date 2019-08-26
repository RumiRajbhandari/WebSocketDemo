import { Router } from 'express';
import * as ordersController from '../controllers/ordersControllers';

const router = Router();

router.get('/',ordersController.fetchAll);

router.get('/filter', ordersController.fetchById);

router.post('/', ordersController.saveOrders);

export default router;