package com.hugman.culinaire.objects.block.cauldron;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.state.property.IntProperty;

import java.util.Map;

public abstract class AbstractLeveledCauldronBlock extends AbstractCauldronBlock {
	public AbstractLeveledCauldronBlock(Settings settings, Map<Item, CauldronBehavior> behaviorMap) {
		super(settings, behaviorMap);
		this.setDefaultState(this.stateManager.getDefaultState().with(getLevelProperty(), 1));
	}

	public int getLevel(BlockState state) {
		return state.get(getLevelProperty());
	}

	public BlockState defaultWithLevel(int amount) {
		return setLevel(getDefaultState(), amount);
	}

	public BlockState setLevel(BlockState state, int amount) {
		int i = Math.min(amount, getMaxLevel());
		return i <= 0 ? Blocks.CAULDRON.getDefaultState() : state.with(getLevelProperty(), i);
	}

	public BlockState changeLevel(BlockState state, int amount) {
		return setLevel(state, getLevel(state) + amount);
	}

	public abstract IntProperty getLevelProperty();

	public abstract int getMaxLevel();

	@Override
	public boolean isFull(BlockState state) {
		return state.get(getLevelProperty()) == getMaxLevel();
	}
}
