package com.aura.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class AuraMainScreen extends Screen {
    private final int menuWidth = 480;
    private final int menuHeight = 300;
    private int startX;
    private int startY;
    private int selectedTab = 0;

    public AuraMainScreen() {
        super(Text.literal("Aura Client"));
    }

    @Override
    protected void init() {
        super.init();
        this.startX = (this.width - menuWidth) / 2;
        this.startY = (this.height - menuHeight) / 2;

        int tabX = startX + 10;
        int tabY = startY + 50;

        // Используем наши новые кнопки с анимацией!
        this.addDrawableChild(new AuraButton(tabX, tabY, 100, 20, Text.literal("Combat"), button -> this.selectedTab = 0));
        this.addDrawableChild(new AuraButton(tabX, tabY + 30, 100, 20, Text.literal("Visuals"), button -> this.selectedTab = 1));
        this.addDrawableChild(new AuraButton(tabX, tabY + 60, 100, 20, Text.literal("Interface"), button -> this.selectedTab = 2));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Размытие фона
        this.applyBlur(delta);
        
        // 2. Черный фон меню (0xEE - непрозрачность, 050505 - почти черный цвет)
        context.fill(startX, startY, startX + menuWidth, startY + menuHeight, 0xEE050505);

        // 3. Линии разделения колонок
        context.drawVerticalLine(startX + 120, startY + 40, startY + menuHeight - 10, 0xFF333333);
        context.drawVerticalLine(startX + 300, startY + 40, startY + menuHeight - 10, 0xFF333333);

        // 4. Заголовки
        context.drawTextWithShadow(this.textRenderer, "Категории", startX + 10, startY + 15, 0xAAAAAA);
        context.drawTextWithShadow(this.textRenderer, "Модули", startX + 130, startY + 15, 0xAAAAAA);
        context.drawTextWithShadow(this.textRenderer, "Настройки", startX + 310, startY + 15, 0xAAAAAA);

        // 5. Текст в зависимости от вкладки (заглушки)
        if (selectedTab == 0) {
            context.drawTextWithShadow(this.textRenderer, "KillAura", startX + 130, startY + 50, 0xFFFFFF);
        } else if (selectedTab == 1) {
            context.drawTextWithShadow(this.textRenderer, "FullBright", startX + 130, startY + 50, 0xFFFFFF);
        } else if (selectedTab == 2) {
            context.drawTextWithShadow(this.textRenderer, "Theme: Dark", startX + 130, startY + 50, 0xFFFFFF);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false; // Игра не встает на паузу, чтобы видеть размытый мир
    }
}
