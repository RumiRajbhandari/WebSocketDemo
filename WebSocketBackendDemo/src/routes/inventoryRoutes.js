import { Router } from 'express';
import * as inventoryController from '../controllers/inventoryControllers';

const router = Router();

router.get('/',inventoryController.fetchAll);

export default router;